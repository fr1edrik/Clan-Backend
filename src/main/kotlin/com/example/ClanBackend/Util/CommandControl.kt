package com.example.ClanBackend.Util

import com.example.ClanBackend.interfaces.ICommand

object CommandControl {
    val GetCpuUsage: ICommand = com.example.ClanBackend.Util.GetCpuUsage
    val GetMemUsage: ICommand = com.example.ClanBackend.Util.GetMemUsage
}