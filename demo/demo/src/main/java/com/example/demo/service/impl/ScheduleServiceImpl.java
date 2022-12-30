package com.example.demo.service.impl;

import com.example.demo.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public String testSchedule() {

        return "ok";
    }

    /**
     * <kien>
     * Ex: hệ thống của bạn có 1 yêu cầu,
     * đó là sau khi deploy, cứ mỗi ngày cần phải gửi báo cáo về số lượng người mua hàng tới email của nhà bán hàng.
     * --------->Như vậy, ta có thể sử dụng schedule với fixedRate là 84600
     * (Trong đoạn code dưới đây, tôi để là 2000ms để ta có thể nhìn dễ hơn )
     * @return -> 2s thì message log "Send email to producers to inform quantity sold items"
     * </kien>
     */
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
        // call send email method here
        log.info("FixedRate 2s");
    }

    /**
     * <kien>
     * Ex::::::::
     * Cùng với bài toán ở phía trên, nhưng thay đổi 1 chút.
     * Đó là thay vì mỗi giờ bạn gửi email, thì giờ bạn muốn thay đổi là,
     * khi tác vụ gửi email đó hoàn thành, 1 tiếng sau đó, lại thực hiện lại tác vụ gửi email. Cứ như thế...
     * Nó khác gì với yêu cầu thứ phía trên?
     * Khác là ở chỗ
     *
     * Ở scheduleTaskWithFixedRate, nếu bạn deploy app thành công lúc 12h,
     * thì tác vụ gửi mail sẽ được gọi đầu tiên lúc 12h trưa, sau đó 1h, 2h, 3h .v.v.
     * là những mốc thời gian mà tác vụ gửi mail đc thực hiện
     *
     * Ở scheduleTaskWithFixedDelay, khác 1 chút, vẫn là 12h deploy thành công,
     * 12h thực hiện tác vụ gửi mail đầu tiên.
     * Nhưng nếu việc gửi mail mất 5p, thì 1 tiếng sau đó (tức là 1h5p) thì tác vụ gửi mail mới dc gọi lần thứ 2,
     * lần này gửi mail lại mất 10p (tức là 1h15p ms hoàn thành) thì phải chờ tới 2h15p mới được gọi lại
     *
     * @return ->  message log "Send email to producers to inform quantity sold items"
     * </kien>
     */
    @Scheduled(fixedDelay = 10000)
    public void scheduleTaskWithFixedDelay() {
        // Call send email method here
        // Pretend it takes 1000ms
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("FixedDelay 10s");
    }

    /**
     * <kien>
     * Ex::::::::
     * Gần giống như fixedRate, Ở đây chỉ khác là, nếu như fixedRate bạn chạy tác vụ gửi mail ngay khi deploy xong,
     * thì initialDelay cho phép bạn thực hiện việc này sau 1 khoảng thời gian là initialDelay(miliseconds)
     * @return -> 5s sau khi deploy , sau đó cứ 2s thì ghi log
     * </kien>
     */
    @Scheduled(fixedRate = 2000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        log.info("-->InitialDelay");
    }

    /**
     * <kien>
     * Ex::::::::
     *     Thay đổi cron expression để phù hợp với yêu cầu của hệ thống.
     *     Thể tham khảo trên https://www.freeformatter.com/cron-expression-generator-quartz.html
     * @return -> 5s sau khi deploy , sau đó cứ 2s thì ghi log
     * </kien>
     */
    @Scheduled(cron = "10-20 * * * * ? ")
    public void scheduleTaskWithCronExpression() {
        log.info("<<<<<<<<<<<<<<<<<<<<<<<<cron 10-20 of every second>>>>>>>>>>>>>");
    }
}
