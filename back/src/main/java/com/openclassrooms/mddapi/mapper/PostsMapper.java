package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostsDto;
import com.openclassrooms.mddapi.models.Posts;
import com.openclassrooms.mddapi.repository.TopicsRepository;
import com.openclassrooms.mddapi.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * The type Post Mapper.
 */

@Component
public class PostsMapper extends ModelMapper {

    private final UsersRepository usersRepository;

    private final TopicsRepository topicsRepository;


    public PostsMapper(UsersRepository usersRepository, TopicsRepository topicsRepository) {
        this.usersRepository = usersRepository;
        this.topicsRepository = topicsRepository;
        this.toEntityConfiguration();
        this.toDtoConfiguration();
    }

    private void toEntityConfiguration() {
        this.typeMap(PostsDto.class, Posts.class)
                .addMappings(src -> src.using(context -> {
                    Long topicsId = (Long) context.getSource();
                    return this.topicsRepository.findById(topicsId).orElseThrow();
                }).map(PostsDto::getTopics, Posts::setTopics));
    }

    private void toDtoConfiguration() {
        this.typeMap(Posts.class, PostsDto.class)
                .addMapping(src -> src.getAuthor().getUsername(), PostsDto::setAuthor)
                .addMapping(src -> src.getTopics().getId(), PostsDto::setTopics);
    }


    public Iterable<PostsDto> mapToDtoList(Iterable<Posts> posts) {
        List<PostsDto> postsDtoList = new ArrayList<>();

        for (Posts post : posts) {
            PostsDto postsDto = this.map(post, PostsDto.class);
            postsDto.setAuthor(post.getAuthor().getUsername());
            postsDto.setTopics(post.getTopics().getId());
            postsDtoList.add(postsDto);
        }

        return postsDtoList;
    }

}
