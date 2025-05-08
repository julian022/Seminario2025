package com.inventario.demo.controller;

import com.inventario.demo.model.MovimientoInventario;
import com.inventario.demo.service.MovimientoInventarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoService;

    @GetMapping(path="/mostrartodos")
    public List<MovimientoInventario> getAllMovimientos() {
        return movimientoService.findAll();
    }

    @PostMapping(path="/crearmovimiento")
    @ResponseStatus(HttpStatus.CREATED)
    public MovimientoInventario createMovimiento(@RequestBody MovimientoInventario movimiento) {
        return movimientoService.save(movimiento);
    }

    @GetMapping(path="/mostrarmovimiento/{id}")
    public MovimientoInventario getMovimientoById(@PathVariable Long id) {
        return movimientoService.findById(id);
    }

    @PutMapping(path="/actualizarmovimiento/{id}")
    public MovimientoInventario updateMovimiento(@PathVariable Long id, @RequestBody MovimientoInventario movimientoDetails) {
        MovimientoInventario movimiento = movimientoService.findById(id);
        if (movimiento != null) {
            movimiento.setCantidad(movimientoDetails.getCantidad());
            movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
            movimiento.setMaterial(movimientoDetails.getMaterial());
            return movimientoService.save(movimiento);
        }
        return null;
    }

    @DeleteMapping(oath="borrarmovimiento/{id}")
    public void deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteById(id);
    }
}
