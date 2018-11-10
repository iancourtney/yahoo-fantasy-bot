package services;

import utils.Log;
import utils.Postgres;

public abstract class Service {
    private static final Log log = new Log(Service.class);

    private boolean shouldUse = true;

    final String url;


    public Service(String url) {
        this.url = url;
    }

    /**
     * Creates a message.  Recursively creates messages when they are too large.
     *
     * @param message the message to be sent
     */
    public abstract void createMessage(String message);

    /**
     * Sends a message to the group.
     *
     * @param message the message to send
     */
    abstract void sendMessage(String message);

    public void shouldNotUse() {
        this.shouldUse = false;
    }

    public boolean shouldUse() {
        return shouldUse;
    }

    String correctMessage(String message) {
        try {
            Thread.sleep(1000);
            if (message.endsWith("\\")) {
                message = message.substring(0, message.length() - 1);
            } else if (message.startsWith("n")) {
                message = message.substring(1);
            }

            return message;
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage(), true);

            return null;
        }
    }
}