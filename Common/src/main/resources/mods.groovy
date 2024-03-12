import modsdotgroovy.Dependency

ModsDotGroovy.make {
    def modid = this.buildProperties["mod_id"]

    modLoader = "javafml"
    loaderVersion = "[1,)"

    license = "MIT"
    issueTrackerUrl = ""

    mod {
        modId = modid
        displayName = this.buildProperties["mod_name"]
        version = this.version
        group = this.group
        authors = [this.buildProperties["authors"] as String]

        displayUrl = ""
        sourcesUrl = ""
        logoFile = "logo.png"
        description = ""

        onFabric {
            entrypoints {
                main = ""
                client = ""
            }
        }

        dependencies {
            onForge {
                minecraft = this.minecraftVersionRange
                forge = ">=${this.libs.versions.neoforge}"
            }

            onFabric {
                minecraft = this.minecraftVersion
                fabricloader = ">=${this.fabricLoaderVersion}"
                mod('fabric-api') {
                    versionRange = ">=${this.libs.versions.fabric.api.split("+")[0]}"
                }
            }
        }

        onForge {
            dependencies = dependencies.collect { dep ->
                new Dependency() {
                    @Override
                    Map asForgeMap() {
                        def map = dep.asForgeMap()
                        def mandatory = map.mandatory
                        map.remove('mandatory')
                        map.put('type', mandatory ? 'required' : 'optional')
                        return map
                    }
                }
            }
        }
    }

    onFabric {
        environment = "*"
        mixin = [
                modid + ".mixins.json"
        ]
    }
}