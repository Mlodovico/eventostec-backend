package com.eventostec.api.adapters.outbound.repositories;

import com.eventostec.api.adapters.outbound.entities.JpaEventEntity;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventRepositoryImpl implements EventRepository {

    private JpaEventRepository jpaEventRepository;

    public EventRepositoryImpl(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        JpaEventEntity jpaEventEntity = new JpaEventEntity(event);
        this.jpaEventRepository.save(jpaEventEntity);
    }

    @Override
    public Event findById(UUID id) {
        Optional<JpaEventEntity> eventEntity = this.jpaEventRepository.findById(id);
        return eventEntity.map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate())).orElse(null);
    }

    @Override
    public List<Event> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(UUID id) {

    }
}
