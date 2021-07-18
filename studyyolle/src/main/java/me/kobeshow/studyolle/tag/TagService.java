package me.kobeshow.studyolle.tag;

import lombok.RequiredArgsConstructor;
import me.kobeshow.studyolle.domain.Tag;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag findOrCreateNew(String tagTitle) {
        Tag tag = tagRepository.findByTitle(tagTitle).orElseThrow(() -> new RuntimeException("태그 정보가 없습니다."));
        if (tag == null) {
            tag = tagRepository.save(Tag.builder().title(tagTitle).build());
        }

        return tag;
    }
}
