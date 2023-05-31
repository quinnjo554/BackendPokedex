////package com.example.pokemonBackend.Security
////
////import com.example.pokemonBackend.Security.JwtAuthConverterProperties
////import org.springframework.core.convert.converter.Converter
////import org.springframework.security.authentication.AbstractAuthenticationToken
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.oauth2.jwt.Jwt
//import org.springframework.security.oauth2.jwt.JwtClaimNames
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
//import java.util.*
//import java.util.stream.Collectors
//import java.util.stream.Stream
//
//class JwtAuthConverter(private val properties: JwtAuthConverterProperties) : Converter<Jwt, AbstractAuthenticationToken> {
//
//    private val jwtGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()
//
//    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
//        val authorities = Stream.concat(
//                jwtGrantedAuthoritiesConverter.convert(jwt)?.stream() ?: Stream.empty(),
//                extractResourceRoles(jwt)?.stream() ?: Stream.empty()
//        ).collect(Collectors.toSet())
//        return JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt))
//    }
//
//
//    private fun getPrincipalClaimName(jwt: Jwt): String {
//        var claimName = JwtClaimNames.SUB
//        properties.principalAttribute?.let {
//            claimName = it
//        }
//        return jwt.getClaim(claimName)
//    }
//
//    private fun extractResourceRoles(jwt: Jwt): Collection<GrantedAuthority> {
//        val resourceAccess = jwt.getClaim<Map<String, Any>>("resource_access")
//        val resource = resourceAccess?.get(properties.resourceId) as? Map<String, Any>
//        val resourceRoles = resource?.get("roles") as? Collection<String>
//        return resourceRoles?.stream()
//                ?.map { role -> SimpleGrantedAuthority("ROLE_$role") }
//                ?.collect(Collectors.toSet()) ?: emptySet()
//    }
//}
