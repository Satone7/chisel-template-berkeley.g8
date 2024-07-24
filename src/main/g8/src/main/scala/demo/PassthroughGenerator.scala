package demo

import chisel3._

// Chisel Code, but pass in a parameter to set widths of ports
class PassthroughGenerator(width: Int) extends Module {
  val io = IO(new Bundle {
    val in = Input(UInt(width.W))
    val out = Output(UInt(width.W))
  })
  io.out := io.in

  printf("Print during simulation: Input is %d\n", io.in)
  // chisel printf has its own string interpolator too
  printf(p"Print during simulation: IO is \$io\n")
}

// Let's now generate modules with different widths
object PassthroughGenerator extends App {
  println(getVerilogString(new PassthroughGenerator(10)))
  println(getVerilogString(new PassthroughGenerator(20)))
}
