package $package$.models

case class Item(name: String)
case class Response(id: Long, text: String, item: Item)
