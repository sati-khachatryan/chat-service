package com.polixis.task.response;

import java.util.List;

public record MessageSentResponse(long from, List<Long> tos, String message) {
}
