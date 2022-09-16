package io.bs.gitlist

import io.bs.gitlist.repositorylist.domain.RepositoryListFacade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AcceptanceSpec extends IntegrationSpec {
    @Autowired
    RepositoryListFacade repositoryListFacade

    def "successful repository fetching scenario"() {
        when: 'I go to /users/bswiercz/repos'
        ResultActions getRepositories = mockMvc.perform(get("/users/bswiercz/repos"))

        then: 'I get repositories'
        getRepositories.andExpect( status().isOk() )
                .andExpect(jsonPath("\$[*].name").value(["DatAcq","ftdispi","gitlist","Inz","magicnumbers","SenServer","syko_proj"]))
    }
}
