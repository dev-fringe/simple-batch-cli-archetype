package dev.fringe.support;

import java.util.Date

class TypeHelper {
  def check(clazz: AnyRef) = clazz match {
      case _: String => "String"
      case _: Date => "Date"
      case _: Number => "Number"
      case _ => "others"
    }
}