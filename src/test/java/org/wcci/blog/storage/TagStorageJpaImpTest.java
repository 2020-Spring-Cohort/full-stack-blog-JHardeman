package org.wcci.blog.storage;


import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TagStorageJpaImpTest {

    @Test
    public void shouldStoreTag(){
        TagRepository tagRepository = mock(TagRepository.class);
        TagStorage underTest = new TagStorageJpaImp(tagRepository);
        Tag testTag = new Tag("Cool");
        underTest.store(testTag);
        verify(tagRepository).save(testTag);



    }
}
