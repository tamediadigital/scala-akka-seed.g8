package $package$.errors

/**
  * Error codes
  * Note: maybe change this to Enum
  */
object ErrorCode {
  val notFound = "error.endpoint.not.found"
  val internalError = "error.internal.exception"
  val invalidJson = "error.invalid.json"
  val jsonMissingField = "error.json.missing.field"
  val notSupportedMethod = "error.not.supported.method"
}
