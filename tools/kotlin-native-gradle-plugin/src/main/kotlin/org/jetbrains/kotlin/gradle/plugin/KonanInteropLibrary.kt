/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.gradle.plugin

import org.gradle.api.Task
import org.gradle.api.file.FileCollection
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.internal.reflect.Instantiator
import org.jetbrains.kotlin.gradle.plugin.tasks.KonanInteropTask
import org.jetbrains.kotlin.konan.target.KonanTarget
import java.io.File

open class KonanInteropLibrary(name: String, project: ProjectInternal, instantiator: Instantiator)
    : KonanBuildingConfig<KonanInteropTask>(name, KonanInteropTask::class.java, project, instantiator), KonanInteropSpec {

    override fun generateTaskDescription(task: KonanInteropTask) =
            "Build the Kotlin/Native interop library '${task.name}' for target '${task.konanTarget}'"

    override fun generateAggregateTaskDescription(task: Task) =
            "Build the Kotlin/Native interop library '${task.name}' for all supported and declared targets'"

    override fun generateHostTaskDescription(task: Task, hostTarget: KonanTarget) =
            "Build the Kotlin/Native interop library '${task.name}' for current host"

    override val defaultBaseDir: File
        get() = project.konanLibsBaseDir

    // DSL

    override fun defFile(file: Any) = forEach { it.defFile(file) }

    override fun packageName(value: String) = forEach { it.packageName(value) }

    override fun compilerOpts(vararg values: String) = forEach { it.compilerOpts(*values) }

    override fun headers(vararg files: Any) = forEach { it.headers(*files) }

    override fun headers(files: FileCollection) = forEach { it.headers(files) }

    override fun includeDirs(vararg values: Any) = forEach { it.includeDirs(*values) }

    override fun linkerOpts(values: List<String>) = forEach { it.linkerOpts(values) }
    override fun linkerOpts(vararg values: String) = linkerOpts(values.toList())

    override fun link(vararg files: Any) = forEach { it.link(*files) }
    override fun link(files: FileCollection) = forEach { it.link(files) }
}