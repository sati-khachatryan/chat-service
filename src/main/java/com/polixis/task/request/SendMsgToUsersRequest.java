package com.polixis.task.request;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class SendMsgToUsersRequest {

    @NotNull
    private Message message;
    @NotEmpty
    private List<Long> usersIds;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public List<Long> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<Long> usersIds) {
        this.usersIds = usersIds;
    }
}
