package com.bindschaedel.controller;

import com.bindschaedel.entity.Classification;
import com.bindschaedel.entity.Club;
import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.entity.Rating;
import com.bindschaedel.entity.ShowRating;
import com.bindschaedel.service.ClassificationService;
import com.bindschaedel.service.ClubService;
import com.bindschaedel.service.GroupService;
import com.bindschaedel.service.RatingService;
import com.bindschaedel.service.ShowRatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {

    private final GroupService          groupService;
    private final ClubService           clubService;
    private final ClassificationService classificationService;
    private final ShowRatingService     showRatingService;
    private final RatingService         ratingService;

    public GroupController(GroupService groupService, ClubService clubService, ClassificationService classificationService,
                           ShowRatingService showRatingService, RatingService ratingService) {
        this.groupService = groupService;
        this.clubService = clubService;
        this.classificationService = classificationService;
        this.showRatingService = showRatingService;
        this.ratingService = ratingService;
    }

    @GetMapping("/groups")
    public ResponseEntity<Iterable<ClubGroup>> getAllGroups() {
        return new ResponseEntity<>(groupService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/groups/{groupId}")
    public ResponseEntity<ClubGroup> getGroupById(@PathVariable(value = "groupId") String groupId) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @PostMapping("/groups")
    public ResponseEntity<ClubGroup> createGroup(@RequestBody ClubGroup group) {
        if (group == null || group.getClub() == null) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        Club clubObject = clubService.findById(group.getClub().getId());
        group.setClub(clubObject);
        group.setTime(group.getTime());
        ClubGroup savedGroup = groupService.save(group);

        HttpStatus status = savedGroup == null ? HttpStatus.CONFLICT : HttpStatus.CREATED;
        return new ResponseEntity<>(savedGroup, status);
    }

    @PostMapping("/groups/{groupId}/classification/{classificationId}")
    public ResponseEntity<ClubGroup> updateGroupClassification(@PathVariable(value = "groupId") String groupId,
                                                               @PathVariable(value = "classificationId") String classificationId) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        Classification classification = classificationService.findById(Long.parseLong(classificationId));
        if (group == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else if (classification == null) {
            return new ResponseEntity<>(group, HttpStatus.CONFLICT);
        }
        else {
            group.setClassification(classification);
            ClubGroup savedGroup = groupService.save(group);
            return new ResponseEntity<>(savedGroup, HttpStatus.OK);
        }
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable(value = "groupId") String groupId) {
        if (groupService.findById(Long.parseLong(groupId)) != null) {
            groupService.remove(Long.parseLong(groupId));
            return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
        }
        return new ResponseEntity<>(Boolean.FALSE, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/groups/{groupId}/time")
    public ResponseEntity<ClubGroup> updateTime(@PathVariable(value = "groupId") String groupId, @RequestBody TimeUpdateRequestBody time) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        try {
            group.setTime(LocalTime.parse(time.getTime()));
            ClubGroup updatedGroup = groupService.save(group);
            return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
        }
        catch (DateTimeParseException dateTimeParseException) {
            return new ResponseEntity<>(group, HttpStatus.CONFLICT);
        }

    }

    @Transactional
    @PostMapping("/groups/{groupId}/rating")
    public ResponseEntity<ClubGroup> updateRating(@PathVariable(value = "groupId") String groupId,
                                                  @RequestBody RatingUpdateRequestBody ratingUpdateRequestBody) {
        ClubGroup group = groupService.findById(Long.parseLong(groupId));
        ShowRating showRating = new ShowRating();
        List<Rating> ratingList = new ArrayList<>();
        ratingUpdateRequestBody.getRatings().forEach((rating) -> {
            Rating tmpRating = new Rating();
            tmpRating.setValue(rating);
            ratingList.add(tmpRating);
        });
        showRating.setRatings(ratingList);
        showRating.setGroup(group);
        showRatingService.deleteByGroupId(Long.parseLong(groupId));
        ShowRating savedShowRating = showRatingService.save(showRating);
        group.setShowRating(savedShowRating);
        ratingList.forEach((rating -> {
            rating.setShowRating(showRating);
            ratingService.save(rating);
        }));
        ClubGroup updatedGroup = groupService.save(group);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    private static class TimeUpdateRequestBody {
        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    private static class RatingUpdateRequestBody {
        private List<Double> ratings;

        public List<Double> getRatings() {
            return ratings;
        }

        public void setRatings(List<Double> ratings) {
            this.ratings = ratings;
        }
    }
}
