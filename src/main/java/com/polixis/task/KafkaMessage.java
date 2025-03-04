package com.polixis.task;

public record KafkaMessage(long from, long to, String message) {
}
