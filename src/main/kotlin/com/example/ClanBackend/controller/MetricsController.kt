package com.example.ClanBackend.controller

import com.example.ClanBackend.Util.CommandControl
import com.example.ClanBackend.model.metrics.Memory
import com.example.ClanBackend.model.metrics.Metrics
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.management.ManagementFactory


@RestController
class MetricsController {
    @GetMapping("/metrics", produces = ["application/json"])
    fun metric(): Metrics {
        val mxBean = ManagementFactory.getMemoryMXBean()
        val threadMXBean = ManagementFactory.getThreadMXBean()

        return Metrics(CommandControl.GetCpuUsage.execute() as String, CommandControl.GetMemUsage.execute() as Memory)
        // return Metrics(threadMXBean.allThreadIds.map { threadMXBean.getThreadCpuTime(it) }, mxBean.heapMemoryUsage)
    }
}