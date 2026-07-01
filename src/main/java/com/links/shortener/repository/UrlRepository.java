package com.links.shortener.repository;

import com.links.shortener.entity.Url;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

}