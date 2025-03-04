package com.polixis.task;

import com.polixis.task.request.Message;
import com.polixis.task.request.SendMsgToUsersRequest;
import com.polixis.task.response.MessageSentResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.util.List;

@Path("/messages")
public class MessageResource {
    @Inject
    UserService userService;

    @Channel("messages")
    Emitter<KafkaMessage> emitter;


    @POST
    @Path("to")
    public Response sendMessage(@Context SecurityContext securityContext, SendMsgToUsersRequest sendMsgToUsersRequest) {
        String sender = securityContext.getUserPrincipal().getName();
        long senderId = userService.findByUsername(sender).id;
        userService.findByIds(sendMsgToUsersRequest.getUsersIds())
                .stream()
                .map(user -> new KafkaMessage(senderId, user.id, sendMsgToUsersRequest.getMessage().message()))
                .forEach(emitter::send);

        return Response.ok().status(200).entity(
                new MessageSentResponse(senderId, sendMsgToUsersRequest.getUsersIds(),
                        sendMsgToUsersRequest.getMessage().message())).build();
    }


    @POST
    @Path("to-all")
    public Response sendMessageToAll(@Context SecurityContext securityContext, Message message) {
        String sender = securityContext.getUserPrincipal().getName();
        long senderId = userService.findByUsername(sender).id;
        List<Long> list = userService.findAll().stream().map(userEntity -> userEntity.id)
                .filter(id -> !id.equals(senderId))
                .toList();
        SendMsgToUsersRequest sendMsgToUsersRequest = new SendMsgToUsersRequest();
        sendMsgToUsersRequest.setUsersIds(list);
        sendMsgToUsersRequest.setMessage(message);

        return sendMessage(securityContext, sendMsgToUsersRequest);
    }
}
