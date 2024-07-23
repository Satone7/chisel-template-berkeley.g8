package demo

import chisel3._
import chiseltest._
import chisel3.stage.ChiselStage.emitFirrtl // deprecated
// import circt.stage.ChiselStage.emitSystemVerilog
import org.scalatest.flatspec.AnyFlatSpec

class PassthroughTest extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "PassthroughGenerator"
  it should "pass through bits" in {
    test(new PassthroughGenerator(3)) { c =>
      c.io.in.poke(0.U)     // Set our input to value 0
      c.io.out.expect(0.U)  // Assert that the output correctly has 0
      c.io.in.poke(1.U)     // Set our input to value 1
      c.io.out.expect(1.U)  // Assert that the output correctly has 1
      c.io.in.poke(2.U)     // Set our input to value 2
      c.io.out.expect(2.U)  // Assert that the output correctly has 2
    }
    test(new PassthroughGenerator(10)) { c =>
      c.io.in.poke(0.U)
      c.io.out.expect(0.U)
      c.io.in.poke(1023.U)
      c.io.out.expect(1023.U)
    }
    test(new PassthroughGenerator(20)) { c =>
      c.io.in.poke(0.U)
      c.io.out.expect(0.U)
      c.io.in.poke(1048575.U)
      c.io.out.expect(1048575.U)
    }
    println("SUCCESS!!") // Scala Code: if we get here, our tests passed!
    println(getVerilogString(new PassthroughGenerator(10)))
    println(emitFirrtl(new PassthroughGenerator(10)))
  }
}
