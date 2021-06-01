package me.kobeshow.javamockito.study;

import me.kobeshow.javamockito.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {
}
