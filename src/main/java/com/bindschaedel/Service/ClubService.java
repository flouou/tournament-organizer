package com.bindschaedel.Service;

import com.bindschaedel.Entity.ClubEntity;
import com.bindschaedel.Repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubService {
    @Autowired
    private ClubRepository clubRepository;

    public Iterable<ClubEntity> create(String name, String date, int number, String topic, int order){
        ClubEntity clubEntity = new ClubEntity();
        clubEntity.setName(name);
        clubEntity.setDate(date);
        clubEntity.setNumber(number);
        clubEntity.setTopic(topic);
        clubEntity.setClubOrder(order);
        //clubRepository.save(clubEntity);

        return clubRepository.findAll();
    }

    public Iterable<ClubEntity> getAll(){
        return clubRepository.findAll();
    }

    public void updateOrder(int clubOrder, int id){
        clubRepository.updateClubOrder(clubOrder,id);
    }
}
