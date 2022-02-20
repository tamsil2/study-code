package me.kobeshow.studyolle.modules.study;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import me.kobeshow.studyolle.modules.tag.QTag;
import me.kobeshow.studyolle.modules.tag.Tag;
import me.kobeshow.studyolle.modules.zone.QZone;
import me.kobeshow.studyolle.modules.zone.Zone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Set;

import static me.kobeshow.studyolle.modules.account.QAccount.*;
import static me.kobeshow.studyolle.modules.study.QStudy.*;
import static me.kobeshow.studyolle.modules.tag.QTag.*;
import static me.kobeshow.studyolle.modules.zone.QZone.*;

public class StudyRepositoryExtensionImpl extends QuerydslRepositorySupport implements StudyRepositoryExtension{

    public StudyRepositoryExtensionImpl() {
        super(Study.class);
    }

    @Override
    public Page<Study> findByKeyword(String keyword, Pageable pageable) {
        JPQLQuery<Study> query = from(study).where(study.published.isTrue()
                .and(study.title.containsIgnoreCase(keyword))
                .or(study.tags.any().title.containsIgnoreCase(keyword))
                .or(study.zones.any().localNameOfCity.containsIgnoreCase(keyword)))
                .leftJoin(study.tags, tag).fetchJoin()
                .leftJoin(study.zones, zone).fetchJoin()
                .distinct();
        JPQLQuery<Study> pageableQuery = getQuerydsl().applyPagination(pageable, query);
        QueryResults<Study> fetchResults = pageableQuery.fetchResults();
        return new PageImpl<>(fetchResults.getResults(), pageable, fetchResults.getTotal());
    }

    @Override
    public List<Study> findByAccount(Set<Tag> tags, Set<Zone> zones) {
        QStudy study = QStudy.study;
        JPQLQuery<Study> query = from(study).where(study.published.isTrue()
                .and(study.closed.isFalse())
                .and(study.tags.any().in(tags))
                .and(study.zones.any().in(zones)))
                .leftJoin(study.tags, tag).fetchJoin()
                .leftJoin(study.zones, zone).fetchJoin()
                .orderBy(study.publishedDateTime.desc())
                .distinct()
                .limit(9);
        return query.fetch();
    }
}
