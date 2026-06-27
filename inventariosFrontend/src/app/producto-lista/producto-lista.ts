import { Component, inject } from '@angular/core';
import { Producto } from '../model/producto.model';
import { ProductoServicio } from '../servicios/producto.service';
import { CurrencyPipe } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-producto-lista',
  imports: [CurrencyPipe],
  templateUrl: './producto-lista.html'
})
export class ProductoLista {
  productos !: Producto[];

  private productoServicio = inject(ProductoServicio);
  private cdr = inject(ChangeDetectorRef);
  private enrutador = inject(Router);

  ngOnInit() {
    this.obtenerProductos();
}

private obtenerProductos(): void{
  this.productoServicio.obtenerProductosLista().subscribe({
    next:(datos) =>{
      // console.log('Productos recibidos:', datos);
      // console.log('Cantidad:', datos.length);
      this.productos = datos;
      this.cdr.detectChanges();
    },
    error: (error) => console.error('Error al obtener la lista de productos:', error)
  });
}

editarProducto(id: number ){
  this.enrutador.navigate(['editar-producto', id]);
}

eliminarProducto(id: number){
  this.productoServicio.eliminarProducto(id).subscribe({
    next: (datos) => this.obtenerProductos(),
    error: (error) => console.log("Error al eliminar producto: ", error)
  });
}
}