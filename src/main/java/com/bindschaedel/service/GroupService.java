package com.bindschaedel.service;

import com.bindschaedel.entity.ClubGroup;
import com.bindschaedel.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Iterable<ClubGroup> getAll() {
        return groupRepository.findAll();
    }

    public ClubGroup findById(Long id) {
        Optional<ClubGroup> groupOptional = groupRepository.findById(id);
        return groupOptional.orElseGet(ClubGroup::new);
    }

    public List<ClubGroup> findGroupByClubId(Long id) {
        return groupRepository.findByClubId(id);
    }

    public ClubGroup save(ClubGroup group) {
        return groupRepository.save(group);
    }

    public void removeAll() {
        groupRepository.deleteAll();
    }

}
