@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    public ResponseEntity<Material> crearMaterial(@RequestBody Material material) {
        Material nuevoMaterial = materialService.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMaterial);
    }

    @PutMapping("/{id}/reducir-stock")
    public ResponseEntity<Material> reducirStock(
            @PathVariable Long id,
            @RequestParam int cantidad) {
        
        Material materialActualizado = materialService.reducirStock(id, cantidad);
        return ResponseEntity.ok(materialActualizado);
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Material>> obtenerMaterialesConStockBajo() {
        List<Material> materiales = materialService.findByStockActualLessThanStockMinimo();
        return ResponseEntity.ok(materiales);
    }
}