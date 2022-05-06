package com.suyh5504.websocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OutputMessage {
    private String from;
    private String text;
    private String time;
}
