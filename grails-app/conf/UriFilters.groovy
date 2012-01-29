
class UriFilters {
	def filters = {
		uriFilter(uri: "/sec/**") {
			after = { model ->
				model << [addedFromFilter:"foo"]	
			}
		}
	}
}