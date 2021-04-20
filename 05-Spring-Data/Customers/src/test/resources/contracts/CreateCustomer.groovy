package contracts

import org.springframework.cloud.contract.spec.Contract;

Contract.make {
    description "Create customer"

    request {
        method 'POST'
        url '/customers'
        headers {
            contentType(applicationJson())
        }
        body([
                firstName: "example",
                lastName : "example",
                active   : true
        ]
        )
    }
    response {
        status 201
    }
}
