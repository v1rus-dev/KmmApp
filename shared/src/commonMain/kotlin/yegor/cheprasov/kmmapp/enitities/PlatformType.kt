package yegor.cheprasov.kmmapp.enitities

sealed class PlatformType {
    class PC(val released: String?): PlatformType()
    class Xbox(val released: String?): PlatformType()
    class Playstation4(val released: String?): PlatformType()
    class Playstation5(val released: String?): PlatformType()
    class NintendoSwitch(val released: String?): PlatformType()
    class Other(val released: String?): PlatformType()
}