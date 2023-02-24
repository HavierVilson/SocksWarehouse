package ru.viatsuk.sockswarehouse.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.viatsuk.sockswarehouse.model.Color;
import ru.viatsuk.sockswarehouse.model.Size;
import ru.viatsuk.sockswarehouse.model.Socks;
import ru.viatsuk.sockswarehouse.services.impl.SocksStockServiceImpl;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "Socks market", description = "CRUD операции для работы со складом носков.")
public class SocksMarketController {
    private SocksStockServiceImpl socksStockService;

    public SocksMarketController(SocksStockServiceImpl socksStockService) {
        this.socksStockService = socksStockService;
    }

    @GetMapping("")
    @Operation(summary = "Вывод количества товара.", description = "Вывод количества носков по заданным параметрам.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Выведено количество товара по запросу."),
            @ApiResponse(responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    })
    public ResponseEntity<Object> getSocks(@RequestParam Color color,
                                           @RequestParam Size size,
                                           @RequestParam int cottonMin,
                                           @RequestParam int cottonMax){
        int socksCount = socksStockService.getSocks(color, size, cottonMin, cottonMax);
        if(socksCount == 0){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socksCount);
    }


    @PostMapping()
    @Operation(summary = "Приход товара на склад.", description = "Добавление носков на склад по их параметрам.")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200",
                    description = "Товар добавлен на склад.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    })
    public ResponseEntity<Socks> addSocks (@RequestBody @Valid Socks socks) {
        socksStockService.addSocks(socks);
        return ResponseEntity.ok().body(socks);
    }

    @PutMapping()
    @Operation(summary = "Отпуск товара со склада.",
            description = "Получение носков со склада по параметрам, на складе количество уменьшается.")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200",
                    description = "Товар получен со склада.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    })
    public ResponseEntity<Socks> editSocks(@RequestBody @Valid Socks socks){
        Socks socks1 = socksStockService.editSocksFromStock(socks);
        if(socks1 == null){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(socks1);
    }

    @DeleteMapping()
    @Operation(summary = "Списание товара со склада.",
            description = "Получение носков со склада по параметрам, на складе количество уменьшается.")
    @ApiResponses (value = {
            @ApiResponse(responseCode = "200",
                    description = "Товар списан со склада.",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Socks.class))
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "Параметры запроса отсутствуют или имеют некорректный формат."),
            @ApiResponse(responseCode = "500",
                    description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    })
    public ResponseEntity<Void> deleteSocks(@RequestBody @Valid Socks socks){
        if(socksStockService.removeSocks(socks)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
