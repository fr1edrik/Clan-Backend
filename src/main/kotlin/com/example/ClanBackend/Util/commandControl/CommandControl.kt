package com.example.ClanBackend.Util.commandControl

import com.example.ClanBackend.interfaces.ICommand
import com.example.ClanBackend.model.metrics.Memory

object CommandControl {
    val GetCpuUsage: ICommand<String> = com.example.ClanBackend.Util.commandControl.GetCpuUsage
    val GetMemUsage: ICommand<Memory> = com.example.ClanBackend.Util.commandControl.GetMemUsage
}