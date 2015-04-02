package com.sonarsource.rulefinder;

import com.google.common.collect.ImmutableList;
import org.sonar.api.SonarPlugin;

import java.util.List;

public final class RuleFinderPlugin extends SonarPlugin {

  public List getExtensions() {
    return ImmutableList.of(RuleWs.class);
  }


}
