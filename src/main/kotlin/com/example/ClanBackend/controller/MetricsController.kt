package com.example.ClanBackend.controller

import com.example.ClanBackend.Util.commandControl.CommandControl
import com.example.ClanBackend.model.metrics.Memory
import com.example.ClanBackend.model.metrics.Metrics
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class MetricsController {
    @CrossOrigin(origins = ["http://localhost:3000"])
    @GetMapping("/metrics", produces = ["application/json"])
    fun getMetrics(): Metrics {
        return Metrics(CommandControl.GetCpuUsage.execute(), CommandControl.GetMemUsage.execute())
    }
}