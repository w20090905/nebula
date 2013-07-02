package test.DependencyInjection.dagger;

import dagger.Module;
import dagger.Provides;

@Module(complete = false, library = true)
class PumpModule {
  @Provides Pump providePump(Thermosiphon pump) {
    return pump;
  }
}
