package me.nansha
package examples.18.6 - VirtualFileSystem.shared.src

import upickle.default.{Reader, Writer}
object Shared{
  def send[T: Writer](out: java.io.DataOutputStream, msg: T): Unit = {
    val bytes = upickle.default.writeBinary(msg)
    out.writeInt(bytes.length)
    out.write(bytes)
    out.flush()
  }
  def receive[T: Reader](in: java.io.DataInputStream) = {
    val buf = new Array[Byte](in.readInt())
    in.readFully(buf)
    upickle.default.readBinary[T](buf)
  }
  def hashPath(p: os.Path) = {
    if (!os.isFile(p)) None
    else Some(java.util.Arrays.hashCode(os.read.bytes(p)))
  }
}
