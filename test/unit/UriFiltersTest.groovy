import grails.test.mixin.*
import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletRequest

@TestFor(UriController)
@Mock(UriFilters)
class UriFiltersTest {

	void testFilterAddedAttribute() {
		def model
		GrailsMockHttpServletRequest mockHttpServletRequest = request
		// all controllers are mapped to /sec/**
		// UriFilters is mapped to /sec/**
		mockHttpServletRequest.requestURI = '/sec/uri/index'
		withFilters([:]) {
				model = controller.index()
		}
		assert model.addedFromController == 'bar'
		assert model.addedFromFilter == 'foo'
	}
}
