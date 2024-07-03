package pe.gob.pnsu.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

@Slf4j
public class JavaWebSocketClient extends WebSocketClient {

    public JavaWebSocketClient(URI serverUri){
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        //System.out.println("Connected");
        log.info("connected");
    }

    @Override
    public String onMessage(String s) {

        String message= s;
        log.info("got: "+s);

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        log.info("disconnected");
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
    }
}
