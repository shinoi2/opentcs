// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.access.rmi.factories;

import static java.util.Objects.requireNonNull;

import jakarta.inject.Inject;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.rmi.ssl.SslRMIServerSocketFactory;
import org.opentcs.access.SslParameterSet;

/**
 * Provides instances of {@link RMIClientSocketFactory} and {@link RMIServerSocketFactory} that are
 * implemented over the SSL or TLS protocols.
 * Since these factories don't support anonymous cipher suites a keystore on the server-side and a
 * truststore on the client-side is necessary.
 */
public class SecureSocketFactoryProvider
    implements
      SocketFactoryProvider {

  /**
   * Provides methods for creating client-side and server-side {@link SSLContext} instances.
   */
  private final SecureSslContextFactory secureSslContextFactory;

  /**
   * Creates a new instance.
   *
   * @param sslParameterSet The SSL parameters to be used for creating socket factories.
   */
  @Inject
  public SecureSocketFactoryProvider(SslParameterSet sslParameterSet) {
    requireNonNull(sslParameterSet, "sslParameterSet");
    this.secureSslContextFactory = new SecureSslContextFactory(sslParameterSet);
  }

  @Override
  public RMIClientSocketFactory getClientSocketFactory() {
    return new CustomSslRMIClientSocketFactory(secureSslContextFactory);
  }

  @Override
  public RMIServerSocketFactory getServerSocketFactory() {
    SSLContext context = secureSslContextFactory.createServerContext();
    SSLParameters param = context.getSupportedSSLParameters();
    return new SslRMIServerSocketFactory(
        context,
        param.getCipherSuites(),
        param.getProtocols(),
        param.getWantClientAuth()
    );
  }
}
