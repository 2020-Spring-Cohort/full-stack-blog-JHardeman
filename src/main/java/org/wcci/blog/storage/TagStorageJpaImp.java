package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Collection;

@Service
public class TagStorageJpaImp implements TagStorage {
    private TagRepository tagRepository;


    public TagStorageJpaImp(TagRepository tagRepository){
        this.tagRepository = tagRepository;
    }


    @Override
    public Collection<Tag> getAll() {
        return (Collection<Tag>) tagRepository.findAll();
    }

    @Override
    public void store(Tag tagToStore) {
        tagRepository.save(tagToStore);

    }

    @Override
    public Tag findTagById(Long id) {
        return tagRepository.findById(id).get();
    }


    @Override
    public Collection<Tag> findAllTags(){
        return (Collection<Tag>) tagRepository.findAll();
    }

    @Override
    public Tag findTagByName(String name){
        return tagRepository.findByName(name).get();
    }

    @Override
    public Tag findTagById(long id) {
        return tagRepository.findById(id).get();
    }
}
