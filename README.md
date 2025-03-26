# es-syscall-filter-patch
Disable the check for Elasticsearch syscall filter

## Overview

This Java agent disables the syscall filter check in Elasticsearch 8+. 

In Elasticsearch versions prior to 8.0, it was possible to disable the system call filter check using the `bootstrap.system_call_filter: false` option in `elasticsearch.yml`. However, this option was removed in Elasticsearch 8.

The problem is that if the syscall filters cannot be installed for any reason (for example, when Elasticsearch is not running on Linux), Elasticsearch will refuse to start.

This Java agent circumvents that check by patching the code to always return `true` from the `isSystemCallFilterInstalled` method, allowing Elasticsearch to start even when syscall filters cannot be properly installed.

## Building

To build the agent:

```shell
mvn clean package
```

To use, set this envvar:

```shell
export ES_JAVA_OPTS="$ES_JAVA_OPTS -javaagent:/path/to/es-syscall-patch-1.0-SNAPSHOT.jar"
```