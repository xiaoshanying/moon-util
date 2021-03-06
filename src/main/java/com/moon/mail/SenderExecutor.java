package com.moon.mail;

import com.moon.util.CPUUtil;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.*;

/**
 * @author benshaoye
 */
public class SenderExecutor {

    private final static ThreadPoolExecutor executor;

    static {
        int core = 1;
        int max = CPUUtil.getCoreCount();
        long timeout = 60 * 1000;
        int queueSize = 32;
        BlockingQueue queue = new ArrayBlockingQueue(queueSize);

        executor = new ThreadPoolExecutor(core, max, timeout, TimeUnit.MILLISECONDS, queue,
            Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        executor.allowsCoreThreadTimeOut();
    }

    static void sendMail(MimeMessage message) {
        executor.submit(() -> {
            try {
                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
