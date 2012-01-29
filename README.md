Grails Test App for unit testing uri based filters
================================

This is a vanilla grails app.
Added:
 * UriFilters.groovy
 * UriController.groovy
 * UriFiltersTest.groovy
 
Modified:
 * UrlMappings.groovy
 
Installation
------------
Clone the repo

Usage:
------
run: grails run-app
visit: http://localhost:8080/uri-filter-unit-test/sec/uri/index

run: grails test-app


UrlMapping
----------

	class UrlMappings {

		static mappings = {
			"/sec/$controller/$action?/$id?"{
				constraints {
					// apply constraints here
				}
			}

			"/"(view:"/index")
			"500"(view:'/error')
		}
	}


UriFilter
---------

	class UriFilters {
		def filters = {
			uriFilter(uri: "/sec/**") {
				after = { model ->
					model << [addedFromFilter:"foo"]	
				}
			}
		}
	}
    
UriController
-------------

	class UriController {
		def index (){
			[addedFromController:"bar"]
		}
	}


UriFilterTests
--------------

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

