package org.gogroup.server;

import java.io.*;
import java.net.Socket;

public class Client
{
    private PrintWriter clientPrintWriter;
    private BufferedReader clientBufferedReader;

    private InputStream clientInputStream;

    private volatile boolean waitingForResponse;

    public void connect() throws IOException
    {
        Socket clientSocket = new Socket("localhost", 7777);
        this.clientPrintWriter = new PrintWriter(clientSocket.getOutputStream(), true); //
        this.clientInputStream = clientSocket.getInputStream();
        this.clientBufferedReader = new BufferedReader(new InputStreamReader(clientInputStream)); //
    }

    public void write(String content)
    {
        System.out.println(content);
        this.clientPrintWriter.println(content);
    }

    public String read()
    {
        try {
            if (this.clientInputStream.available() < 2) {
                return null;
            }
            String line = clientBufferedReader.readLine();
            System.out.println(line);
            return line;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized boolean isWaitingForResponse()
    {
        return this.waitingForResponse;
    }

    public synchronized void setWaitingForResponse(boolean waitingForResponse)
    {
        this.waitingForResponse = waitingForResponse;
    }
}