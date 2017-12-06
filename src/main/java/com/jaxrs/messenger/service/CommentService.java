package com.jaxrs.messenger.service;

import com.jaxrs.messenger.database.DataBase;
import com.jaxrs.messenger.model.Comment;
import com.jaxrs.messenger.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentService {

    private final Map<Long, Message> messageMap = DataBase.getMessages();

    public List<Comment> getAllComments(long messageId) {
        return new ArrayList<>(messageMap.get(messageId).getComments().values());
    }

    public Optional<Comment> getComment(final long messageId, final long comentId) {
        Optional<Comment> comment = messageMap.get(messageId).getComments().values().stream().filter(x -> x.getId() == comentId).findAny();
        return comment;
    }

    public Comment addComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messageMap.get(messageId).getComments();
        comment.setId(comments.size() + 1);
        comments.put(comment.getId(), comment);
        return comment;
    }

    public Optional<Comment> updateComment(long messageId, Comment comment) {
        Map<Long, Comment> comments = messageMap.get(messageId).getComments();
        if (comment.getId() <= 0) {
            return Optional.empty();
        }
        comments.put(comment.getId(), comment);
        return Optional.of(comment);
    }

    public void deleteComment(long messageId, long commentId) {
        messageMap.get(messageId).getComments().remove(commentId);
    }
}
