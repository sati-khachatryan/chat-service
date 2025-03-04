package com.polixis.task.response;

import jakarta.ws.rs.core.Response.Status;

public record ExResponse(Status status, String msg) {
}
