package tech.viacom.junitvsspock

import com.google.common.io.Resources
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.See
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Test Structure Specification")
@Narrative("""As a developer
              I want to die
              so I can end the pain""")
class S00b_TestStructureSpec extends Specification {

    void setup() {
        println "setting up a single spec"
    }

    void cleanup() {
        println "cleaning up a single spec"
    }

    void setupSpec() {
        println "setting up the whole spec"
    }

    void cleanupSpec() {
        println "cleaning up the whole spec"
    }

    @Issue("https://jira.mtvi.com/browse/VIP-1320")
    @See("https://confluence.mtvi.com/pages/viewpage.action?pageId=295543225")
    def "test inserting ships"() {
        given: "we have some ships"
        def ship1 = new Ship("Enterprise", "Federation")
        def ship2 = new Ship("M'Char", "Klingon")
        def ship3 = new Ship("Constitution", "Federation")

        and: "an instance of in memory ship store"
        @Subject def shipStore = new MemoryShipStore()

        expect: "the store is initially empty"
        shipStore.list().size() == 0

        when: "we insert some ships to the store"
        shipStore.insert(ship1)
        shipStore.insert(ship2)
        shipStore.insert(ship3)

        then: "we can list the added ships"
        def ships = shipStore.list()
        ships.size() == 3
    }

    @Rule TemporaryFolder temporaryFolder = new TemporaryFolder()

    def "can copy a resource from classpath to a file"() {
        given:
        def resource = Resources.getResource("test.txt")
        def file = temporaryFolder.newFile()

        when:
        resource.withReader { file << it }

        then:
        println temporaryFolder.root.absolutePath
        println file.text
        println resource.text
        file.text == resource.text
    }

    def "can use multiple when and then blocks"() {
        given:
        def stack = new Stack()

        when:
        stack.push "foo"

        then:
        stack.pop() == "foo"

        expect:
        stack.empty()

        when:
        stack.pop()

        then:
        thrown EmptyStackException
    }
}
