package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentsDto;
import com.openclassrooms.mddapi.models.Comments;
import com.openclassrooms.mddapi.repository.PostsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Post Mapper extends ModelMapper.
 */
@Component
public class CommentsMapper extends ModelMapper {

    private final PostsRepository postsRepository;

    public CommentsMapper(PostsRepository postsRepository) {
        this.toEntityConfiguration();
        this.toDtoConfiguration();
        this.postsRepository = postsRepository;
    }

    private void toEntityConfiguration() {
        this.typeMap(CommentsDto.class, Comments.class)
                .addMappings(src -> src.using(context -> {
                    Long postsId = (Long) context.getSource();
                    return this.postsRepository.findById(postsId).orElseThrow();
                }).map(CommentsDto::getPosts, Comments::setPosts));
    }

    private void toDtoConfiguration() {
        this.typeMap(Comments.class, CommentsDto.class)
                .addMapping(src -> src.getAuthor().getUsername(), CommentsDto::setAuthor)
                .addMapping(src -> src.getPosts().getId(), CommentsDto::setPosts);
    }


    public Iterable<CommentsDto> mapToDtoList(Iterable<Comments> comments) {
        List<CommentsDto> commentsDtoList = new ArrayList<>();
        for (Comments comment : comments) {
            CommentsDto commentDto = this.map(comment, CommentsDto.class);
            commentDto.setAuthor(comment.getAuthor().getUsername());
            commentsDtoList.add(commentDto);
        }

        return commentsDtoList;
    }
}
