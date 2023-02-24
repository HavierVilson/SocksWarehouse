package ru.viatsuk.sockswarehouse.services;

import ru.viatsuk.sockswarehouse.model.Color;
import ru.viatsuk.sockswarehouse.model.Size;
import ru.viatsuk.sockswarehouse.model.Socks;

public interface SocksStockService {
    void addSocks(Socks socks);

    int getSocks(Color color, Size size, int cottonMin, int cottonMax);

    Socks editSocksFromStock(Socks socks);

    boolean removeSocks(Socks socks);
}
