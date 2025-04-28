import { Component, input, effect, signal, ElementRef } from '@angular/core';
import QRCode from 'qrcode';

@Component({
  selector: 'app-qr-code',
  imports: [],
  templateUrl: './qr-code.component.html',
  styleUrl: './qr-code.component.css'
})
export class QrCodeComponent {
  value = input<string>("")
  data = signal("")

  constructor(element: ElementRef) {
    effect(async () => {
      const canvas = (element.nativeElement as HTMLDivElement).children[0] as HTMLCanvasElement;
      const size = 100; // is quadratic, so width = height
      const margin = 5;
      const triangleSize = 20
      QRCode.toCanvas(canvas, this.value(), { margin: margin, width: size })

      const ctx = canvas.getContext("2d")!
      ctx.fillStyle = 'blue';
      ctx.beginPath();
      ctx.moveTo(size, size);
      ctx.lineTo(size - triangleSize, size);
      ctx.lineTo(size, size - triangleSize);
      ctx.fill();
      ctx.strokeStyle = 'blue'
      ctx.lineWidth = 5;
      ctx.strokeRect(0, 0, size, size);
    })
  }
}
