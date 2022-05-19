package yegor.cheprasov.kmmapp.android

import androidx.annotation.DrawableRes
import yegor.cheprasov.kmmapp.enitities.PlatformType

sealed class Platform (@DrawableRes val imageRes: Int?, val id: Int){
    class PC(val released: String?): Platform(R.drawable.ic_windows, 1)
    class Xbox(val released: String?): Platform(R.drawable.ic_xbox_logo, 2)
    class Playstation(val released: String?): Platform(R.drawable.ic_playstation, 3)
    class NintendoSwitch(val released: String?): Platform(null, 4)
    class Other(val released: String?): Platform(null, 5)

    companion object {
        fun getPlatformFromPlatformType(platformType: PlatformType): Platform =
            when(platformType) {
                is PlatformType.PC -> PC(platformType.released)
                is PlatformType.Xbox -> Xbox(platformType.released)
                is PlatformType.Playstation4 -> Playstation(platformType.released)
                is PlatformType.Playstation5 -> Playstation(platformType.released)
                is PlatformType.NintendoSwitch -> NintendoSwitch(platformType.released)
                is PlatformType.Other -> Other(platformType.released)
            }
    }
}
