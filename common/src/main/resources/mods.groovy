MultiplatformModsDotGroovy.make {
    def modid = buildProperties["mod_id"]

    modLoader = "javafml"
    onNeoForge {
        loaderVersion = libs.versions.get("javafml_range")
    }

    license = "MIT"
    issueTrackerUrl = ""

    onFabric {
        accessWidener = "${modid}.accesswidener"
    }
    onNeoForge {
        accessTransformers {
            accessTransformer("META-INF/accessTransformer.cfg")
        }
    }

    mod {
        modId = modid
        displayName = buildProperties["mod_name"]
        authors = (buildProperties["authors"] as String).split(",")
        version = environmentInfo.version

        displayUrl = ""
        sourcesUrl = ""
        logoFile = "assets/${modid}/logo.png"
        description = ""

        onFabric {
            entrypoints {
                main = ""
                client = ""
            }
        }

        dependencies {
            onNeoForge {
                mod("neoforge") {
                    versionRange = "${libs.versions.get("neoforge_range")}"
                }
            }
            onFabric {
                minecraft = "${libs.versions.get("minecraft_range")}"
                fabricloader = ">=${libs.versions.get("fabric_loader")}"
                mod("fabric-api") {
                    versionRange = libs.versions.get("fabric_api_range")
                }
            }
        }
    }

    onNeoForge {
        mixins {
            mixin("${modid}.mixins.json")
            mixin("${modid}.neo.mixins.json")
        }
    }

    onFabric {
        environment = Environment.ANY
        mixins {
            mixin("${modid}.mixins.json")
            mixin("${modid}.fabric.mixins.json")
        }
    }
}