package com.eventostec.api.adapters.outbound.repositories;

import com.eventostec.api.adapters.outbound.entities.JpaEventEntity;
import com.eventostec.api.domain.event.Event;
import com.eventostec.api.domain.event.EventAddressProjection;
import com.eventostec.api.domain.event.EventRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class EventRepositoryImpl implements EventRepository {

    private JpaEventRepository jpaEventRepository;

    public EventRepositoryImpl(JpaEventRepository jpaEventRepository) {
        this.jpaEventRepository = jpaEventRepository;
    }

    @Override
    public Event save(Event event) {
        JpaEventEntity jpaEventEntity = new JpaEventEntity(event);
        this.jpaEventRepository.save(jpaEventEntity);
        return new Event(jpaEventEntity.getId(), jpaEventEntity.getTitle(), jpaEventEntity.getDescription(),
                jpaEventEntity.getImgUrl(), jpaEventEntity.getEventUrl(), jpaEventEntity.getRemote(),
                jpaEventEntity.getDate());
    }

    @Override
    public Event findById(UUID id) {
        Optional<JpaEventEntity> eventEntity = this.jpaEventRepository.findById(id);
        return eventEntity.map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(), entity.getEventUrl(), entity.getRemote(), entity.getDate())).orElse(null);
    }

    @Override
    public List<Event> findAll() {
        return this.jpaEventRepository.findAll()
                .stream()
                .map(entity -> new Event(entity.getId(), entity.getTitle(), entity.getDescription(), entity.getImgUrl(),
                        entity.getEventUrl(), entity.getRemote(), entity.getDate()))
                .collect(Collector.of(java.util.ArrayList::new, List::add, (left, right) -> {
                    left.addAll(right);
                    return left;
                }));
    }

    @Override
    public void deleteById(UUID id) {
        this.jpaEventRepository.deleteById(id);
    }

    @Override
    public Page<EventAddressProjection> findUpcomingEvents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaEventRepository.findUpcomingEvents(new Date(), pageable);
    }

    @Override
    public Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate,
            int page,
            int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.jpaEventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);
    }

    @Override
    public List<EventAddressProjection> findEventByTitle(String title) {
        return this.jpaEventRepository.findEventsByTitle(title);
    }
}
